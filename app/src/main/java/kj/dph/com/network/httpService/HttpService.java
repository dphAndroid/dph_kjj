package kj.dph.com.network.httpService;


import kj.dph.com.network.entity.request.LoginReq;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Streaming;
import retrofit2.http.Url;
import rx.Observable;

/**
 * service统一接口数据
 * **************************************
 * 方法注解，包含@GET、@POST、@PUT、@DELETE、@PATH、@HEAD、@OPTIONS、@HTTP。
 * 标记注解，包含@FormUrlEncoded、@Multipart、@Streaming。
 * 参数注解，包含@Query,@QueryMap、@Body、@Field，@FieldMap、@Part，@PartMap。
 * 其他注解，@Path、@Header,@Headers、@Url
 */

public interface HttpService {
    //************************************************样例接口*******************************************************
    /*断点续传下载接口*/
    @Streaming/*大文件需要加入这个判断，防止下载过程中写入到内存中*/
    @GET
    Observable<ResponseBody> download(@Header("RANGE") String start, @Url String url);

    /**
     * POST User/Base/Login 登录
     *
     * @return
     */
    @POST("Base/Login")
    Observable<String> doBaseLogin(@Body LoginReq strList);

}
