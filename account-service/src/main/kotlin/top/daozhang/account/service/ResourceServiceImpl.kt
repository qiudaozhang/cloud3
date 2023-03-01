package top.daozhang.account.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import com.github.yitter.idgen.YitIdHelper
import org.apache.dubbo.config.annotation.DubboService
import org.springframework.transaction.annotation.Transactional
import top.daozhang.account.entity.Resource
import top.daozhang.account.entity.ResourceExtra
import top.daozhang.account.mapper.ResourceMapper
import top.daozhang.account.vo.ResourceVo
import top.daozhang.util.NonNullBeanUtil

/**
 * <p>
 *  资源服务实现类
 * </p>
 *
 * @author 邱道长
 * @since 2023-02-27
 */
@DubboService
@Transactional(rollbackFor = [Exception::class])
open class ResourceServiceImpl : ServiceImpl<ResourceMapper, Resource>(), ResourceService {

    @jakarta.annotation.Resource

    lateinit var resourceExtraService: ResourceExtraService

    override fun createOne(resource: Resource): Boolean {

        // 决定level
        val level = if (resource.pid == null) {
            1
        } else {
            val parent = getById(resource.pid)
            if (parent == null) {
                throw RuntimeException("关联父资源不存在")
            } else {
                parent.level!! + 1
            }
        }
        resource.initData()
        resource.level = level
        save(resource)

        if (level > 1) {
            // 需要增加补充信息
            // 根据level ，需要总共插入 level-1个补充信息
            val extraList = resourceExtraService.findWithRid(resource.pid!!)
            val last = ResourceExtra()
            last.id = YitIdHelper.nextId()
            last.rid = resource.id
            last.pid = resource.pid
            // 和直接的父亲距离固定是1
            last.distance = 1
            val preData = if (extraList.isEmpty()) {
                // 如果是空的，说明只有一级，插入一个数据就行
                listOf()
            } else {
                // 多层
                val extraData = extraList.map {
                    val e = ResourceExtra()
                    NonNullBeanUtil.copyProperties(it, e)
                    e.distance = e.distance?.plus(1)
                    e.id = YitIdHelper.nextId()
                    e.rid = resource.id
                    e
                }
                extraData
            }
            val totalData = preData.plus(last)
            resourceExtraService.saveBatch(totalData)
        }

        return true
    }

    override fun deleteOne(id: Long): Boolean {
        TODO("Not yet implemented")
    }

    override fun findOne(id: Long): ResourceVo? {

        val one = getById(id)
        val root = ResourceVo()
        NonNullBeanUtil.copyProperties(one,root)
        val children = baseMapper.findSonByPid(id)
        // 挂载所有的儿子
        if(children != null && children.isNotEmpty()){
            mounted(root,children)
        }
        return root
    }


    /**
     * @param node 需要往其挂载资源的对象
     * @param children 子资源
     */
    private fun mounted(node: ResourceVo, children: List<ResourceVo>) {
        if(node.mountFinish!!){
            val nodeChildren =node.children
            if(!nodeChildren.isNullOrEmpty()){
                if(nodeChildren.all { it.mountFinish!! }){
                    node.mountFinish = true
                }
                nodeChildren.forEach {
                    mounted(it,children)
                }
            }
        } else {
            if(node.distance == null){
                node.distance = 0
            }

            val subs = children.filter { it.pid == node.id && it.distance!!.toInt()  == (node.distance!!+1) }
            // 直接的儿子一层挂载完毕
            if(subs.isEmpty()){
                node.mountFinish = true
            } else {
                node.children = subs
                subs.forEach { mounted(it,children) }
                if(subs.all { it.mountFinish!! }){
                    node.mountFinish = true
                }
            }

        }

    }


}
