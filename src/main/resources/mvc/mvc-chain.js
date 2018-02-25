/**
 *@user: 180296-Web寻梦狮
 *@date: 2018-01-12 09:03
 *@description: mvc-chain 这个动作链配置文件,与nutz默认的配置文件,仅仅多了一行mvc.LogTimeProcessor
 *  "!org.nutz.integration.shiro.NutShiroProcessor",
 *  crossOriginAdmin 允许跨域=》签名=》验证身份
 */

var chain={
    "default" : {
        "ps" : [
            "com.gree.mvc.LogTimeProcessor",
            "org.nutz.mvc.impl.processor.UpdateRequestAttributesProcessor",
            "org.nutz.mvc.impl.processor.EncodingProcessor",
            "org.nutz.mvc.impl.processor.ModuleProcessor",
            "org.nutz.mvc.impl.processor.ActionFiltersProcessor",
            "org.nutz.mvc.impl.processor.AdaptorProcessor",
            "org.nutz.mvc.impl.processor.MethodInvokeProcessor",
            "org.nutz.mvc.impl.processor.ViewProcessor"
        ],
        "error" : 'org.nutz.mvc.impl.processor.FailProcessor'
    },
    "crossOriginAdmin" : {
        "ps" : [
            "com.gree.mvc.LogTimeProcessor",
            "org.nutz.mvc.impl.processor.UpdateRequestAttributesProcessor",
            "org.nutz.mvc.impl.processor.EncodingProcessor",
            "org.nutz.mvc.impl.processor.ModuleProcessor",
            "com.gree.mvc.CrossOriginProcessor",
            "org.nutz.mvc.impl.processor.ActionFiltersProcessor",
            "com.gree.mvc.AdminProcessor",
            "org.nutz.mvc.impl.processor.AdaptorProcessor",
            "org.nutz.mvc.impl.processor.MethodInvokeProcessor",
            "org.nutz.mvc.impl.processor.ViewProcessor"
        ],
        "error" : 'org.nutz.mvc.impl.processor.FailProcessor'
    }
};