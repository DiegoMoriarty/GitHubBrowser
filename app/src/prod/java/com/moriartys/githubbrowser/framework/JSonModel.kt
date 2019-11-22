package com.moriartys.githubbrowser.framework

import com.google.gson.annotations.SerializedName
import com.moriartys.githubbrowser.domain.RepoData

object JSonModel {

    class RepoSearchResult {
        @SerializedName("items")
        var items = ArrayList<Item>()
    }

    class Item {
        @SerializedName("name")
        var name: String? = null
        @SerializedName("owner")
        var owner = Owner()
        @SerializedName("description")
        var description: String? = null

        fun toDataModel(): RepoData = RepoData(owner.avatarUrl, name, description)

    }

    class Owner {
        @SerializedName("avatar_url")
        var avatarUrl: String? = null
    }

}

