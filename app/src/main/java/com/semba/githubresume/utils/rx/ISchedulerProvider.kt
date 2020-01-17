package com.semba.githubresume.utils.rx

import io.reactivex.Scheduler

/**
 * Created by SeMbA on 2019-12-07.
 */
interface ISchedulerProvider {

    fun computation(): Scheduler

    fun io(): Scheduler

    fun ui(): Scheduler

    fun newThread(): Scheduler

    fun single(): Scheduler
}