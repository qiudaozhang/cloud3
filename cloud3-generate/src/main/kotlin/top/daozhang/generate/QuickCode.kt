package top.daozhang.generate

import com.baomidou.mybatisplus.generator.FastAutoGenerator
import com.baomidou.mybatisplus.generator.config.GlobalConfig
import com.baomidou.mybatisplus.generator.config.OutputFile
import com.baomidou.mybatisplus.generator.config.PackageConfig
import com.baomidou.mybatisplus.generator.config.StrategyConfig
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine
import java.util.*


object QuickCode {

    @JvmStatic
    fun main(args: Array<String>) {

        val outputPath = "e://gencode"
        val url = "jdbc:mysql://127.0.0.1:3354/cloud3"
        val username = "root"
        val password = "root"

        FastAutoGenerator.create(url, username, password)
            .globalConfig { builder: GlobalConfig.Builder ->
                builder.author("邱道长") // 设置作者
                    // 如果是用
                    .enableSwagger() // 开启 swagger 模式
                    .outputDir(outputPath) // 指定输出目录
                    .enableKotlin()// 开启kotlin风格
            }
            .packageConfig { builder: PackageConfig.Builder ->
                builder.parent("top.daozhang") // 设置父包名
                    .moduleName("account") // 设置父包模块名
                    .pathInfo(
                        Collections.singletonMap(
                            OutputFile.xml,
                            outputPath // 输出目录 可以自行修改
                        )
                    ) // 设置mapperXml生成路径
            }
            .strategyConfig { builder: StrategyConfig.Builder ->
                builder.addInclude("auth") // 设置需要生成的表名
                    .addTablePrefix("t_", "c_") // 设置过滤表前缀
                    .serviceBuilder()
                    .formatServiceFileName("%sService")

            }
            .templateEngine(FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
            .execute()
    }
}