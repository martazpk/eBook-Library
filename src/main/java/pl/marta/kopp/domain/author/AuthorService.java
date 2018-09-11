package pl.marta.kopp.domain.author;

import pl.marta.kopp.persistence.AuthorStorageJpa;

public class AuthorService {
    private final AuthorStorageJpa storage;

    public AuthorService(AuthorStorageJpa storage) {
        this.storage = storage;
    }

    public long add(Author author){
        if(storage.isExists(author)) throw new AuthorAlreadyExistsException(author);
        else storage.add(author);
        return author.getId();
    }

    public void delete(long id){
        if(storage.isExists(id)){
            storage.delete(id);
        }else throw new AuthorDoesNotExistException(id);
    }

    public Author get(long id){
        if(storage.isExists(id))
        return storage.get(id);
        else throw new AuthorDoesNotExistException(id);
    }

    public boolean isExists(long id){
        return storage.isExists(id);
    }


}
