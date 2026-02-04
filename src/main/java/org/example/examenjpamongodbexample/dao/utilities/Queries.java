package org.example.examenjpamongodbexample.dao.utilities;

public class Queries {
    public static final String GET_ALL_BATTLES = "from JpaBattleEntity";
    public static final String GET_ALL_FACTIONS = "from JpaFactionEntity";
    public static final String SelectGetByNewspaperAndName = "select a.id_article, a.name_article, a.id_newspaper, a.id_type, t.id_type, t.name, t.description " +
            "from Article a join Type t on a.id_type = t.id_type where a.id_newspaper = ? and a.name_article = ?";
    public static final String SelectGet = "select a.id_article, a.name_article, a.id_newspaper, a.id_type, t.id_type, t.name, t.description " +
            "from Article a join Type t on a.id_type = t.id_type where a.id_article = ?";
    public static final String SelectSave = "insert into Article (name_article, id_newspaper, id_type) values (?, ?, ?)";
    public static final String DeleteArticle = "delete from Article where id_article = ?";
    public static final String DELETE_READARTICLE_BY_ARTICLEID = "delete from JpaReadArticleEntity where id_article = :id_article";

    public static final String Update = "update Article set name_article = ?, id_newspaper = ?, id_type = ? where id_article = ?";
    public static final String SelectFromCrede = "select * from Credentials";
    public static final String SelectGetCrede = "select username, password, id_reader from Credentials where username = ?";

    public static final String GET_ALL_NEWSPAPERS = "from JpaNewspaperEntity";
    public static final String GET_ALL_READERS = "from JpaReaderEntity";


    public static final String GET_READER_BYID = "from JpaReaderEntity where id_reader = ?1";
    public static final String InsertReader = "insert into Reader (name, birth_date) values (?, ?)";
    public static final String InsertReaderCredential = "insert into Credentials (username, password, id_reader) values (?, ?, ?)";
    public static final String DeleteReader = "delete from Reader where id_reader = ?";
    public static final String DELETE_READER_CREDENTIALS = "delete from Credentials where id_reader = ?";
    public static final String DELETE_READER_SUBSCRIPTION = "delete from Subscription where id_reader = ?";
    public static final String DELETE_READER_READACT = "delete from ReadArticle where id_reader = ?";


    public static final String DELETE_ALL_CREDENTIALS = "delete from Credentials";
    public static final String DELETE_ALL_SUBSCRIPTIONS = "delete from Subscription";
    public static final String DELETE_ALL_READERS = "delete from Reader";
    public static final String DELETE_ALL_READACT = "delete from ReadArticle";



    public static final String UpdateReader = "update Reader set name = ?, birth_date = ? where id_reader = ?";
    public static final String GET_ALL_READERS_BY_ARTICLEID = "select ra.reader from JpaReadArticleEntity ra where ra.id_article = :id_article";



    public static final String GET_ALL_READARTICLES_BY_ARTICLEID = "SELECT ra.id_article, AVG(ra.rating) FROM JpaReadArticleEntity ra GROUP BY ra.id_article";
    public static final String SelectReaderAct_By_ArticleId = "select * from ReadArticle where id_article = ?";
    public static final String GET_ReaderAct = "select * from ReadArticle where id_reader = ? and id_article = ? ";
    public static final String SelectAddRating = "insert into JpaReadArticleEntity (id_reader,id_article,rating) values (?1,?2,?3)";
    public static final String SelectFromType = "select * from Type";
    public static final String UpdateRating = "update JpaReadArticleEntity ra set ra.rating = :rating where ra.reader.id_reader = :id_reader and ra.id_article = :id_article";
    public static final String DeleteRating = "delete from JpaReadArticleEntity ra where ra.reader.id_reader = :id_reader and ra.id_article = :id_article";


}
