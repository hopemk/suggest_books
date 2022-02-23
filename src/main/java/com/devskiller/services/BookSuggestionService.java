package com.devskiller.services;

import java.util.Set;

import com.devskiller.model.Author;
import com.devskiller.model.Book;
import com.devskiller.model.Reader;
import java.util.HashSet;
import java.util.stream.Collectors;

class BookSuggestionService {

    private final Set<Book> books;
    private final Set<Reader> readers;

    public BookSuggestionService(Set<Book> books, Set<Reader> readers) {
        this.books = books;
        this.readers = readers;
    }

    Set<String> suggestBooks(Reader reader) {
        Set<Reader> sameAgeReader = readers.stream().filter(r -> r.getAge() == reader.getAge()).collect(Collectors.toSet());
        Set<Book> sameAgeFavourite = new HashSet<>();
        for(Reader reader1: sameAgeReader){
            sameAgeFavourite.addAll(reader1.getFavouriteBooks());
        }
        Set<String> suggestedBooks = books.stream()
                .filter(b -> b.getRating() >= 4)
                .filter(b -> reader.getFavouriteGenres().contains(b.getGenre()))
                .filter(b -> sameAgeFavourite.contains(b))
               .map(b -> b.getTitle())
                .collect(Collectors.toSet());
        return suggestedBooks;
    }

    Set<String> suggestBooks(Reader reader, int rating) {
        Set<Reader> sameAgeReader = readers.stream().filter(r -> r.getAge() == reader.getAge()).collect(Collectors.toSet());
        Set<Book> sameAgeFavourite = new HashSet<>();
        for(Reader reader1: sameAgeReader){
            sameAgeFavourite.addAll(reader1.getFavouriteBooks());
        }
        Set<String> suggestedBooks = books.stream()
                .filter(b -> b.getRating() >= 4)
                .filter(b -> reader.getFavouriteGenres().contains(b.getGenre()))
                .filter(b -> b.getRating() == rating)
                .filter(b -> sameAgeFavourite.contains(b))
                .map(b -> b.getTitle())
                .collect(Collectors.toSet());
        return suggestedBooks;
    }

    Set<String> suggestBooks(Reader reader, Author author) {
        Set<Reader> sameAgeReader = readers.stream().filter(r -> r.getAge() == reader.getAge()).collect(Collectors.toSet());
        Set<Book> sameAgeFavourite = new HashSet<>();
        for(Reader reader1: sameAgeReader){
            sameAgeFavourite.addAll(reader1.getFavouriteBooks());
        }
        Set<String> suggestedBooks = books.stream()
                .filter(b -> b.getRating() >= 4)
                .filter(b -> reader.getFavouriteGenres().contains(b.getGenre()))
                .filter(b -> b.getAuthor() == author)
                .filter(b -> sameAgeFavourite.contains(b))
                .map(b -> b.getTitle())
                .collect(Collectors.toSet());
        return suggestedBooks;
    }

}
