package top.daozhang.util

import org.springframework.beans.BeanUtils
import org.springframework.beans.BeanWrapper
import org.springframework.beans.BeanWrapperImpl

object NonNullBeanUtil {

    fun getNullPropertyNames(source: Any): Array<String?> {
        val src: BeanWrapper = BeanWrapperImpl(source)
        val pds = src.propertyDescriptors
        val emptyNames: MutableSet<String> = HashSet()
        for (pd in pds) {
            val srcValue = src.getPropertyValue(pd.name)
            if (srcValue == null) emptyNames.add(pd.name)
        }
        val result = arrayOfNulls<String>(emptyNames.size)
        if (emptyNames.isNotEmpty()) {
            emptyNames.forEachIndexed { index, e ->
                run {
                    result[index] = e
                }
            }
        }
        return result
    }


    @JvmStatic
    fun copyProperties(src: Any?, target: Any?) {
        if(src != null && target != null){
            BeanUtils.copyProperties(src, target, *getNullPropertyNames(src))
        }
    }
}