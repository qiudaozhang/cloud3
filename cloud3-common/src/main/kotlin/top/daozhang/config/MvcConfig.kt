package top.daozhang.config

import com.fasterxml.jackson.databind.*
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder
import top.daozhang.consts.TimeConst
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

@Configuration
class MvcConfig {

    @Bean
    fun jackson2ObjectMapperBuilderCustomizer(): Jackson2ObjectMapperBuilderCustomizer? {
        val objectMapper = ObjectMapper()
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false) // 忽略不识别字段
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS) // 转换日期字段中间不带"T"
        objectMapper.disable(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE) // 反序列化时不考虑时区
        val serializers: MutableMap<Class<*>, JsonSerializer<*>> = HashMap()
        serializers[LocalDateTime::class.java] = LocalDateTimeSerializer(TimeConst.FORMAT)
        serializers[LocalDate::class.java] = LocalDateSerializer(TimeConst.FORMAT_DATE)
        serializers[LocalTime::class.java] = LocalTimeSerializer(TimeConst.FORMAT_TIME)
        val deserializers: MutableMap<Class<*>, JsonDeserializer<*>> = HashMap()
        deserializers[LocalDateTime::class.java] = LocalDateTimeDeserializer(TimeConst.FORMAT)
        deserializers[LocalDate::class.java] = LocalDateDeserializer(TimeConst.FORMAT_DATE)
        deserializers[LocalTime::class.java] = LocalTimeDeserializer(TimeConst.FORMAT_TIME)
        return Jackson2ObjectMapperBuilderCustomizer { builder: Jackson2ObjectMapperBuilder ->
            builder.serializersByType(
                serializers
            ).deserializersByType(deserializers).configure(objectMapper)
        }
    }
}