package n.xm.test;

/**
 * @Author: xumeng
 * @Date: 2019/12/7 10:58
 */

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.junit.Test;

/**
 *
 */

public class QiNiuTest {
    /**
     *七斗牛上传文件
     */
    @Test
    public void QiNiuTest()
    {
        //构造一个带指定 Region 对象的配置类
        Configuration cfg = new Configuration(Zone.zone0());
        //...其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
        //...生成上传凭证，然后准备上传
        String accessKey = "6f6OrGFnu9r1QGnH8uLlgOfhCLEC68t_nUjg4dFK";
        String secretKey = "2b5j1EP620afUGMIIhdP_T5pcZGAVgUeLHOnq2yc";
        // 七牛云的存储空间名称
        String bucket = "xm-test-1";
        //如果是Windows情况下，格式是 D:\\qiniu\\test.png
        String localFilePath = "I:\\javaEE项目\\czjk\\day04\\素材\\图片资源\\";
        //默认不指定key的情况下，以文件内容的hash值作为文件名
        String key = null;
        //  授权
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);
        try {
            Response response = uploadManager.put(localFilePath, key, upToken);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            System.out.println(putRet.key);
            System.out.println(putRet.hash);
        } catch (QiniuException e) {
            Response response = e.response;
            System.out.println(response.toString());
            try {
                System.out.println(response.bodyString());
            } catch (QiniuException e1) {
                e1.printStackTrace();
            }

        }
    }

    /**
     * 七斗牛删除文件
     */


    @Test
    public void delete(){
        //构造一个带指定 Region 对象的配置类
        Configuration cfg = new Configuration(Zone.zone2());
//...其他参数参考类注释
        String accessKey = "6f6OrGFnu9r1QGnH8uLlgOfhCLEC68t_nUjg4dFK";
        String secretKey = "2b5j1EP620afUGMIIhdP_T5pcZGAVgUeLHOnq2yc";
        // 七牛云的存储空间名称
        String bucket = "xm-test-1";
        // 文件名称
        String key = "your file key";
        Auth auth = Auth.create(accessKey, secretKey);
        BucketManager bucketManager = new BucketManager(auth, cfg);
        try {
            bucketManager.delete(bucket, key);
        } catch (QiniuException ex) {
            //如果遇到异常，说明删除失败
            System.err.println(ex.code());
            System.err.println(ex.response.toString());

        }
    }
}



