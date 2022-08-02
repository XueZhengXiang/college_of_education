import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.vod.model.v20170321.RefreshUploadVideoRequest;
import com.aliyuncs.vod.model.v20170321.RefreshUploadVideoResponse;

/**
 *
 * 音频或视频刷新上传凭证的示例
 */
public class AudioOrVideoRefreshUploadDemo {

    //填入AccessKey信息
    public static DefaultAcsClient initVodClient(String accessKeyId, String accessKeySecret) throws ClientException {
        String regionId = "cn-shanghai";  // 点播服务接入地域
        DefaultProfile profile = DefaultProfile.getProfile(regionId, accessKeyId, accessKeySecret);
        DefaultAcsClient client = new DefaultAcsClient(profile);
        return client;
    }

    /**
     * 刷新音/视频上传凭证
     * @param client 发送请求客户端
     * @return RefreshUploadVideoResponse 刷新音/视频上传凭证响应数据
     * @throws Exception
     */
    public static RefreshUploadVideoResponse refreshUploadVideo(DefaultAcsClient client) throws Exception {
        RefreshUploadVideoRequest request = new RefreshUploadVideoRequest();
        //音频或视频ID
        request.setVideoId("d51255f0082f43f2ab9121b6372333c0");
        return client.getAcsResponse(request);
    }

    // 请求示例
    public static void main(String[] argv)  {

        try {
            DefaultAcsClient client = initVodClient("LTAI5t7ACfNm3TyyvWRnsJRt", "JBmkIiO0PbpbBCM5OQt8ITcM9TAq5t");
            RefreshUploadVideoResponse response = refreshUploadVideo(client);
            System.out.print("VideoId = " + response.getVideoId() + "\n");
            System.out.print("UploadAddress = " + response.getUploadAddress() + "\n");
            System.out.print("UploadAuth = " + response.getUploadAuth() + "\n");
            System.out.print("RequestId = " + response.getRequestId() + "\n");
        } catch (Exception e) {
            System.out.print("ErrorMessage = " + e.getLocalizedMessage());
        }
    }
}