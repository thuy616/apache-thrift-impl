import json
import sys
from movieservice.ttypes import *

reload(sys)
sys.setdefaultencoding('utf-8')
sys.path.append('gen-py')


def read_movie_service_database():
    movies_list = []
    # movies = Movies(
    #     movies=[]
    # )
    with open("movie_service_db.json", "rb") as data:
        for item in json.load(data)["movies"]:

            # print("item data: " + str(item))

            movie = Movie(
                adult=item["adult"],
                backdrop_path=item["backdrop_path"] if item["backdrop_path"] is not None else "",
                budget=item["budget"],
                genres=[],
                homepage=item["homepage"] if item["homepage"] is not None else "",
                id=item["id"],
                imdb_id=item["imdb_id"],
                original_language=item["original_language"],
                original_title=item["original_title"],
                overview=item["overview"],
                popularity=item["popularity"],
                poster_path=item["poster_path"] if item["poster_path"] is not None else "",
                production_companies=[],
                production_countries=[],
                release_date=item["release_date"],
                revenue=item["revenue"],
                runtime=item["runtime"],
                spoken_languages=[],
                status=item["status"],
                tagline=item["tagline"],
                title=item["title"],
                video=item["video"],
                vote_average=item["vote_average"],
                vote_count=item["vote_count"]
            )

            if item["belongs_to_collection"] is not None:
                c = Collection
                c.id = item["belongs_to_collection"]["id"]
                c.name = item["belongs_to_collection"]["name"]
                if item["belongs_to_collection"]["poster_path"] is not None:
                    c.poster_path = item["belongs_to_collection"]["poster_path"]
                if item["belongs_to_collection"]["backdrop_path"] is not None:
                    c.backdrop_path = item["belongs_to_collection"]["backdrop_path"]

            for genre in item["genres"]:
                movie.genres.append(Genre(
                    id=genre["id"],
                    name=genre["name"]))

            for company in item["production_companies"]:
                movie.production_companies.append(
                    ProductionCompany(
                        name=company["name"],
                        id=company["id"]
                    )
                )

            for country in item["production_countries"]:
                movie.production_countries.append(
                    ProductionCountry(
                        iso_3166_1=country["iso_3166_1"],
                        name=country["name"]
                    )
                )

            for language in item["spoken_languages"]:
                movie.spoken_languages.append(
                    SpokenLanguage(
                        iso_639_1=language["iso_639_1"],
                        name=language["name"]
                    )
                )

            movies_list.append(movie)
            # movies.movies.append(movie)

        print("Finish loading data...")
        data.close()

    return movies_list
