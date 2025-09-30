package hexlet.code;

public enum Status {
    ADDED,      //ключ есть только во втором файле
    REMOVED,    //ключ есть только в первом файле
    UNCHANGED,  //ключ и значение одинаковые
    CHANGED,    //ключ есть в обоих файлах, значения разные
}
