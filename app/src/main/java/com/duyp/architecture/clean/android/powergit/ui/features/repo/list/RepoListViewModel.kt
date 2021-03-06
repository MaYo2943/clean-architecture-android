package com.duyp.architecture.clean.android.powergit.ui.features.repo.list

import com.duyp.architecture.clean.android.powergit.domain.entities.FilterOptions
import com.duyp.architecture.clean.android.powergit.domain.entities.ListEntity
import com.duyp.architecture.clean.android.powergit.domain.entities.repo.RepoEntity
import com.duyp.architecture.clean.android.powergit.domain.usecases.repo.GetUserRepoList
import com.duyp.architecture.clean.android.powergit.ui.base.BasicListViewModel
import com.duyp.architecture.clean.android.powergit.ui.base.adapter.AdapterData
import io.reactivex.Observable
import javax.inject.Inject

class RepoListViewModel @Inject constructor(
        private val mGetUserRepoList: GetUserRepoList
): BasicListViewModel<RepoEntity>(), AdapterData<RepoEntity> {

    private val mFilterOptions = FilterOptions()

    internal var mUsername: String? = null

    override fun refreshAtStartup() = true

    override fun loadList(currentList: ListEntity<RepoEntity>): Observable<ListEntity<RepoEntity>> {
        return if (mUsername != null)
            mGetUserRepoList.getRepoList(currentList, mUsername!!, mFilterOptions).toObservable()
        else
            mGetUserRepoList.getCurrentUserRepoList(currentList, mFilterOptions).toObservable()
    }

    override fun areItemsTheSame(old: RepoEntity, new: RepoEntity) = old == new
}