package com.velocis.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import com.velocis.dao.MediaDao;

import reactor.core.publisher.Mono;

@Service
public class StreamingService {
	@Autowired
    private MediaDao mediaDao;

	private static final String FORMAT = "classpath:video/%s.mp4";//classpath
	
	
	private static final String FORMATREMOTE = "file:D://LocalDocument/%s.mp4"; //for remote location
	private static final String FORMATREMOTEMP3 = "file:D://LocalDocument/%s.mp3"; //for remote location
	
	private static final String FORMAT_REMOTE_PROD = "file:/root/GitHub/Docs/%s.mp4"; //for remote location
	private static final String FORMAT_REMOTE_MP3_PROD = "file:/root/GitHub/Docs/%s.mp3"; //for remote location
	
	
	@Autowired
	private ResourceLoader resourceLoader;

	public Mono<Resource> getVideo(String title) throws InterruptedException {
		/*
		 * Flux.just("a", "b", "c").doOnNext(v ->
		 * System.out.println("before publishOn: " + Thread.currentThread().getName()))
		 * .publishOn(Schedulers.elastic()).doOnNext(v ->
		 * System.out.println("after publishOn: " + Thread.currentThread().getName()))
		 * .subscribeOn(Schedulers.parallel()) .subscribe(v ->
		 * System.out.println("received " + v + " on " +
		 * Thread.currentThread().getName())); Thread.sleep(5000);
		 * 
		 */
		//System.out.println("mono subcription>>>>>>>>>>>>>>>>>>>>"+Mono.subscriberContext());
		return Mono.fromSupplier(() -> resourceLoader.getResource(String.format(FORMAT, title)));
	}
	
	
	public Mono<Resource> getVideoFromFolder(String title) {
		return Mono.fromSupplier(() -> resourceLoader.getResource(String.format(FORMAT_REMOTE_PROD, title)));
}
	public Mono<Resource> getMp3FromFolder(String title) {
		return Mono.fromSupplier(() -> resourceLoader.getResource(String.format(FORMAT_REMOTE_MP3_PROD, title)));
}
	public List<String> getAllFiles() {
		List<String> list= mediaDao.getFileNames();
		
		return list;
}
	
}
