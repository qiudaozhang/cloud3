
rootProject.name = "cloud3"
include("cloud3-common")
include("cloud3-admin")
include("cloud3-gateway")
include("cloud3-api") // 所有接口标准
include("cloud3-api:account-api")
// 子模块里面的子模块
findProject(":cloud3-api:account-api")?.name = "account-api"
// 代码生成
include("cloud3-generate")
include("account-service")
include("system-service")
include("cloud3-api:system-api")
findProject(":cloud3-api:system-api")?.name = "system-api"
include("modules")
