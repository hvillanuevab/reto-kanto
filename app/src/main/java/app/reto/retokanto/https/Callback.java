package app.reto.retokanto.https;

public abstract class Callback<T> {

    public abstract void returnResult(T t);
    public abstract void returnError(String message);
}
