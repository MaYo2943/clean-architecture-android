package com.duyp.architecture.clean.android.powergit.domain.usecases

import com.duyp.architecture.clean.android.powergit.domain.repositories.AuthenticationRepository
import com.duyp.architecture.clean.android.powergit.domain.repositories.SettingRepository
import io.reactivex.Maybe
import javax.inject.Inject

class GetAuthentication @Inject constructor(
        private val mAuthenticationRepository: AuthenticationRepository,
        private val mSettingRepository: SettingRepository
) {

    /**
     * Get current user's authentication token, null if no user or user has been logged out (don't have token stored)
     *
     * @return Maybe which emits current user's authentication token, complete if no current selected user
     */
    fun getCurrentUserAuthentication(): Maybe<String> =
            Maybe.fromCallable { mSettingRepository.getCurrentUsername() }
                    .flatMap { Maybe.fromCallable { mAuthenticationRepository.getAuthentication(it) } }
}