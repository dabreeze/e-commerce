package com.phoenix.service.cloud;

import com.cloudinary.Cloudinary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Map;

@Service("cloudinery")
public class CloudineryServiceImpl implements CloudService {

    @Autowired
    Cloudinary cloudinary;

    @Override
    public Map<?, ?> upload(File file, Map<?, ?> params) throws IOException {
        return cloudinary.uploader().upload(file, params);

    }
}
