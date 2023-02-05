package com.jpmc.a20230130drishtykapoornycschools.presenter

sealed class SortOrder {
    object Ascending: SortOrder()
    object Descending: SortOrder()
}
