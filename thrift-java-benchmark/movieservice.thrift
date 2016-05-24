/**
 * The first thing to know about are types. The available types in Thrift are:
 *
 *  bool        Boolean, one byte
 *  i8 (byte)   Signed 8-bit integer
 *  i16         Signed 16-bit integer
 *  i32         Signed 32-bit integer
 *  i64         Signed 64-bit integer
 *  double      64-bit floating point value
 *  string      String
 *  binary      Blob (byte array)
 *  map<t1,t2>  Map from one type to another
 *  list<t1>    Ordered list of one type
 *  set<t1>     Set of unique elements of one type
 *
 */

 namespace java movieservice

 struct Movies {
    1: list<Movie> movies
 }

 struct Movie {
    1: bool adult,
    2: optional string backdrop_path,
    3: optional Collection belongs_to_collection,
    4: i64 budget,
    5: list<Genre> genres,
    6: optional string homepage,
    7: i64 id,
    8: string imdb_id,
    9: string original_language,
    10: string original_title,
    11: string overview,
    12: double popularity,
    13: optional string poster_path
    14: list<ProductionCompany> production_companies,
    15: list<ProductionCountry> production_countries,
    16: string release_date,
    17: i64 revenue,
    18: i32 runtime,
    19: list<SpokenLanguage> spoken_languages,
    20: string status,
    21: string tagline,
    22: string title,
    23: bool video,
    24: double vote_average,
    25: i32 vote_count
 }

 struct Collection {
    1: i64 id,
    2: string name
    3: string poster_path
    4: string backdrop_path
 }

 struct Genre {
    1: i32 id,
    2: string name
 }

 struct ProductionCompany {
    1: i32 id,
    2: string name
 }

 struct ProductionCountry {
    1: string iso_3166_1,
    2: string name
 }

 struct SpokenLanguage {
    1: string iso_639_1,
    2: string name
 }

 service MovieService {

    Movie getMovie(1:i64 id),

    Movies getMovies(),

    list<Movie> getMovieList()

 }