package com.stefanhodoreanu.libraryapi.service;

import com.stefanhodoreanu.libraryapi.model.Book;
import com.stefanhodoreanu.libraryapi.model.Member;
import com.stefanhodoreanu.libraryapi.repository.BookRepository;
import com.stefanhodoreanu.libraryapi.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final MemberRepository memberRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));
    }

    public Book addBook(Book book) {
        book.setAvailable(true);
        return bookRepository.save(book);
    }

    public Book updateBook(Long id, Book updatedBook) {
        Book book = getBookById(id);
        book.setTitle(updatedBook.getTitle());
        book.setAuthor(updatedBook.getAuthor());
        book.setIsbn(updatedBook.getIsbn());
        return bookRepository.save(book);
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    public Book borrowBook(Long bookId, Long memberId) {
        Book book = getBookById(bookId);
        if (!book.isAvailable()) {
            throw new RuntimeException("Book is not available");
        }
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("Member not found"));
        book.setAvailable(false);
        book.setBorrowedBy(member);
        return bookRepository.save(book);
    }

    public Book returnBook(Long bookId) {
        Book book = getBookById(bookId);
        if (book.isAvailable()) {
            throw new RuntimeException("Book is already available");
        }
        book.setAvailable(true);
        book.setBorrowedBy(null);
        return bookRepository.save(book);
    }

    public List<Book> searchByTitle(String title) {
        return bookRepository.findByTitleContainingIgnoreCase(title);
    }

    public List<Book> getAvailableBooks() {
        return bookRepository.findByAvailableTrue();
    }

}