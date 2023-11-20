package com.azmi.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.azmi.exception.ProductException;
import com.azmi.modal.Product;
import com.azmi.modal.Review;
import com.azmi.modal.User;
import com.azmi.repository.ProductRepository;
import com.azmi.repository.ReviewRepository;
import com.azmi.request.ReviewRequest;

@Service
public class ReviewServiceImplementation implements ReviewService {
	
	private final ReviewRepository reviewRepository;
	private final ProductService productService;
	private final ProductRepository productRepository;
	
	public ReviewServiceImplementation(ReviewRepository reviewRepository,ProductService productService,ProductRepository productRepository) {
		this.reviewRepository=reviewRepository;
		this.productService=productService;
		this.productRepository=productRepository;
	}

	@Override
	public Review createReview(ReviewRequest req,User user) throws ProductException {
		// TODO Auto-generated method stub
		Product product=productService.findProductById(req.getProductId());
		Review review=new Review();
		review.setUser(user);
		review.setProduct(product);
		review.setReview(req.getReview());
		review.setCreatedAt(LocalDateTime.now());
		
//		product.getReviews().add(review);
		productRepository.save(product);
		return reviewRepository.save(review);
	}

	@Override
	public List<Review> getAllReview(Long productId) {
		
		return reviewRepository.getAllProductsReview(productId);
	}

}