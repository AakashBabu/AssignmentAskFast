package com.test.assignment.mvvm.model

data class ListModel(val results:List<ListItem>)
data class ListItem(val vote_count:Int,val id:Int,val video:Boolean,val vote_average:Float,val title: String, val popularity:Float,val poster_path:String,val original_language:String,val original_title:String,val genre_ids:List<Int>, val backdrop_path:String,val adult:Boolean,val overview:String,val release_date:String)

data class DetailModel(val adult:Boolean,val backdrop_path: String,val budget:Long,val genres:List<Genere>,val homepage:String,val id:Int,val imdb_id:String,val original_language:String,val original_title:String,val overview:String,val popularity:Float,val poster_path:String,val production_companies:List<ProductionCompany>,val release_date:String,val revenue:Long,val runtime:Int,val spoken_languages:List<Languages>
                       ,val status:String,val tagline:String,val title:String,val video:Boolean,val vote_average:Float,val vote_count:Int){
    fun getGenere(): String {
        var genere = ""
        for (i in genres){
            if(genere.trim().length == 0)
                genere = i.name
            else
                genere = genere+", "+i.name
        }
        return genere
    }

}
data class Genere(val id:Int,val name:String)
data class CollectionB(val id:Int,val name:String,val backdrop_path:String,val poster_path:String)
data class Languages(val iso_639_1:String,val name:String)
data class ProductionCompany(val id:Int,val logo_path:String,val name:String,val origin_country:String,val iso_3166_1:String)
