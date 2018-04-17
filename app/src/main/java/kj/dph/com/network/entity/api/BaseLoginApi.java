package kj.dph.com.network.entity.api;



import kj.dph.com.network.entity.api.Base.dph.BaseUserApi;
import kj.dph.com.network.entity.request.LoginReq;
import kj.dph.com.network.httpService.HttpService;
import rx.Observable;

/**
 * Created by sky on 2017/5/12.
 */

public class BaseLoginApi extends BaseUserApi {
    public LoginReq req;

    public BaseLoginApi() {
        setMothed("Base/Login");
        setNeedToken(true);
        setShowProgress(true);
    }

    @Override
    public Observable getObservable(HttpService methods) {
        return methods.doBaseLogin(req);
    }
}
