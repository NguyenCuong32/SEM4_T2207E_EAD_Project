package org.example.brofee.services;

import lombok.RequiredArgsConstructor;
import org.example.brofee.dto.EventDto;
import org.example.brofee.entities.Event;
import org.example.brofee.repositories.IEventRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.apache.commons.lang3.RandomStringUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class EventService implements IEventService{
    private final IEventRepository eventRepository;


    @Override
    public List<Event> getAllEvent() {
        return eventRepository.findAll();
    }

    @Override
    public Event getEventById(UUID id) {
        Optional<Event> getEventId = eventRepository.findById(id);
        return getEventId.get();
    }

    public void  saveEventToDB(MultipartFile file,String name,String description,LocalDateTime TimeStart,LocalDateTime timeEnd ,int status)
    {
        Event p = new Event();
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        if(fileName.contains(".."))
        {
            System.out.println("not a a valid file");
        }
        try {
            byte[] bytes = file.getBytes();
            String base64String = Base64.getEncoder().encodeToString(bytes);
            p.setBanner(base64String);
        } catch (IOException e) {
            e.printStackTrace();
        }
        p.setName(name);
        p.setDescription(description);
        p.setTimeStart(TimeStart);
        p.setTimeEnd(timeEnd);
        p.setEventCode(RandomStringUtils.randomAlphanumeric(8));
        p.setStatus(status);

        eventRepository.save(p);
    }

    @Override
    public Event createEvent(EventDto event, MultipartFile bannerUpload) throws IOException {
        Event setEvent = new Event();
        setEvent.setName(event.getName());
        setEvent.setDescription(event.getDescription());
        setEvent.setTimeStart(event.getTimeStart());
        setEvent.setTimeEnd(event.getTimeEnd());
        setEvent.setEventCode(RandomStringUtils.randomAlphanumeric(8));
        setEvent.setStatus(event.getStatus());
        if (bannerUpload != null && !bannerUpload.isEmpty() && bannerUpload.getSize() != 0) {

            String contentType = bannerUpload.getContentType();
            if(contentType != null && contentType.startsWith("image/")){
                String filename = imgBanner(bannerUpload);
                setEvent.setBanner(filename);
            }
        }
        return eventRepository.save(setEvent);
    }

    @Override
    public Event updateEvent(UUID id, EventDto event, MultipartFile bannerUpload) throws IOException {
        Event setEvent = getEventById(id);
        setEvent.setName(event.getName());
        setEvent.setDescription(event.getDescription());
        setEvent.setTimeStart(event.getTimeStart());
        setEvent.setTimeEnd(event.getTimeEnd());
        setEvent.setEventCode(RandomStringUtils.randomAlphanumeric(8));
        setEvent.setStatus(event.getStatus());
        if (bannerUpload != null && !bannerUpload.isEmpty() && bannerUpload.getSize() != 0) {

            String contentType = bannerUpload.getContentType();
            if(contentType != null && contentType.startsWith("image/")){
                String filename = imgBanner(bannerUpload);
                setEvent.setBanner(filename);
            }
        }
        return eventRepository.saveAndFlush(setEvent);
    }

    @Override
    public void deleteEvent(UUID id) {
        Event getEventById = getEventById(id);
        if (!"noname.jpg".equals(getEventById.getBanner())) {
            String uploadsDir = "src/main/resources/static/banner"; // Đường dẫn tới thư mục tải lên
            String filePath = Paths.get(uploadsDir, getEventById.getBanner()).toString();
            File file = new File(filePath);
            if (file.exists()) {
                file.delete();
            }
        }
        eventRepository.deleteById(id);
    }


    private String imgBanner(MultipartFile file) throws IOException {
        if(!isImageFile(file) || file.getOriginalFilename() == null){
            throw new IOException("Invalid image format");
        }
        String filename = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename())); // lay ten anh

        // them UUID vao truoc ten file de doi ten anh

        String uniqueFilename = UUID.randomUUID().toString() + "_" + filename;

        // duong dan den thu muc ma ban muon luu file

        java.nio.file.Path uploadDir = Paths.get("src/main/resources/static/banner");

        if(!Files.exists(uploadDir)){
            Files.createDirectories(uploadDir);
        }

        // duong dan day du den file
        java.nio.file.Path destination = Paths.get(uploadDir.toString(), uniqueFilename);

        // sao chep file vao thu muc dich
        Files.copy(file.getInputStream(),destination, StandardCopyOption.REPLACE_EXISTING);
        return uniqueFilename;
    }

    private boolean isImageFile(MultipartFile file){
        String contentType = file.getContentType();
        return contentType != null && contentType.startsWith("image/");
    }
}
