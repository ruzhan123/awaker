package com.ruzhan.day.source

import com.ruzhan.common.util.CommonUtils
import com.ruzhan.database.CommonAppDatabase
import com.ruzhan.database.CommonModel
import com.ruzhan.day.model.DayNewModel
import com.ruzhan.day.network.DayApi
import com.ruzhan.day.network.DayClient
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class DayRepository {

    companion object {

        private var INSTANCE: DayRepository? = null

        @JvmStatic
        fun get(): DayRepository = INSTANCE ?: synchronized(DayRepository::class) {
            INSTANCE ?: DayRepository().also { INSTANCE = it }
        }
    }

    private val commonAppDatabase: CommonAppDatabase by lazy {
        CommonAppDatabase.invoke(CommonUtils.getContext())
    }
    private val dayApi: DayApi by lazy {
        DayClient.get()
    }

    fun getDayNewList(page: Int, ver: String, appVer: String): Single<List<DayNewModel>> {
        return dayApi.getDayNewList(page, ver, appVer)
                .subscribeOn(Schedulers.io())
    }

    fun getCommonModel(id: Int): Flowable<CommonModel> {
        return commonAppDatabase.commonDao().getCommonModel(id)
    }

    fun insertCommonModel(commonModel: CommonModel) {
        return commonAppDatabase.commonDao().insertCommonModel(commonModel)
    }
}