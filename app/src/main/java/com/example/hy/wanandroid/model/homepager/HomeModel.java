package com.example.hy.wanandroid.model.homepager;

import com.example.hy.wanandroid.contract.homepager.HomeContract;
import com.example.hy.wanandroid.core.network.api.HomeApis;
import com.example.hy.wanandroid.core.network.entity.BaseResponse;
import com.example.hy.wanandroid.core.network.entity.homepager.Articles;
import com.example.hy.wanandroid.core.network.entity.homepager.BannerData;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by 陈健宇 at 2018/10/26
 */
public class HomeModel implements HomeContract.Model {

    private HomeApis mHomeApis;

    @Inject
    public HomeModel(HomeApis homeApis) {
        this.mHomeApis = homeApis;
    }

    @Override
    public Observable<BaseResponse<List<BannerData>>> getBannerDatas() {
        return mHomeApis.getBannerDatas();
    }

    @Override
    public Observable<BaseResponse<Articles>> getArticles(int pageNum) {
        return mHomeApis.getArticles(pageNum);
    }
}
