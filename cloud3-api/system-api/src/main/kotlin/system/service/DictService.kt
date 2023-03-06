package system.service

import com.baomidou.mybatisplus.extension.service.IService
import system.entity.Dict

interface DictService: IService<Dict> {
    fun txSave()
}