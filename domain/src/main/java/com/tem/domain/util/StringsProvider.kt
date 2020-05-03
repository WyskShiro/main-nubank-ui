package com.tem.domain.util

// Declare the strings you want to use in domain and are located in the strings.xml in presentation
interface StringsProvider {
    val title: String
    val confirm: String
    val cancel: String

    val errorNetwork: String
    val errorUnknown: String
    val errorNotFound: String
    val errorSocketTimeout: String

    val globalTryAgain: String

    // Main Menu Options
    val mainMenuOptionHelpMe: String
    val mainMenuOptionProfile: String
    val mainMenuOptionAccountConfigure: String
    val mainMenuOptionCardConfigure: String
    val mainMenuOptionAskPjAccount: String
    val mainMenuOptionAppConfigurations: String

}