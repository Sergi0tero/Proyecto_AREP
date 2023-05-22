package com.example.demo;

import software.amazon.awssdk.auth.credentials.AwsSessionCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.rekognition.RekognitionClient;
import software.amazon.awssdk.services.rekognition.model.*;

import java.util.HashMap;
import java.util.List;

public class CreateCollection {
    public void init() {
        Region region = Region.US_EAST_1;
        AwsSessionCredentials credentials = AwsSessionCredentials.create("ASIA5DF2NVTGSPE2KS76", "fLHirCxxqa/Vhpz2yPuQqKtthb2AqXtj72uLMeZc", "FwoGZXIvYXdzEJj//////////wEaDNJWVkE+jceUpePMmCLaAS0UHi0tjHLio+JITmqQO8ITFjQLitZq+2fJ+h3Rg3catEivVC7bSWKPVIOCT3wSM6jnClMjKOKdwozREFNufX9F2RPdCAaWun5ieN3ThEdhcT0od76cKcKnaDB7HaTSckQn3JcF0DWH16UE3h+P9z2V7LyenOvhPCSy/UXl/5rGtvbWC8TotwtKLhGRBEJ7luKIFiBhOIQNm2GsGF1tz23x57yGkDJ95Ne8+nsHmY5YnfCfIpWZQdBjhGNJtprEYpHT4ApNQTrh1fGx6Z4ZRt59rYRCyyYSd2vIKNi9qqMGMi2oXcey/QJxa4Tqv7OmXb7kx5gcQ5BTC0tHsnK1HqIRE57x3z47vmSBceL5bW0=");


        RekognitionClient rekognitionClient = RekognitionClient.builder()
                .region(region)
                .credentialsProvider(StaticCredentialsProvider.create(credentials))
                .build();
//
//        String collectionId = "areprekog";
//
//        CreateCollectionRequest request = CreateCollectionRequest.builder()
//                .collectionId(collectionId)
//                .build();
//
//        CreateCollectionResponse response = rekognitionClient.createCollection(request);
//
//
//
//        System.out.println("Colección creada: " + response.collectionArn());

        HashMap<String, String> imageAtt = identifyImage("imagen_2023-05-21_191334830.png", rekognitionClient);
        HashMap<String, String> videoAtt = identifyVideo("y2mate.com - hombre llorando meme.mp3", rekognitionClient);

    }

    public HashMap<String, String> identifyImage(String image, RekognitionClient rekognitionClient){
        S3Object s3Object = S3Object.builder()
                .bucket("arepbuck")
                .name(image)
                .build();

        DetectLabelsRequest request2 = DetectLabelsRequest.builder()
                .image(Image.builder().s3Object(s3Object).build())
                .build();

        // Crea la solicitud para iniciar la detección de etiquetas en el archivo de S3
        DetectLabelsResponse response = rekognitionClient.detectLabels(request2);
        List<Label> labels = response.labels();

        System.out.println("Etiquetas detectadas en la imagen:");
        HashMap<String, String> att = new HashMap<>();
        for (Label label : labels) {
            att.put(label.name(), label.confidence().toString());
            System.out.println(label.name() + ": " + label.confidence().toString());
        }
        return att;


    }

    public HashMap<String, String> identifyVideo(String video, RekognitionClient rekognitionClient){
        S3Object s3Object = S3Object.builder()
                .bucket("arepbuck")
                .name(video)
                .build();

        StartLabelDetectionRequest request = StartLabelDetectionRequest.builder()
                .video(Video.builder().s3Object(s3Object).build())
                .build();

        // Envía la solicitud y obtiene la respuesta
        StartLabelDetectionResponse response = rekognitionClient.startLabelDetection(request);
        String jobId = response.jobId();
        System.out.println("Se inició la detección de etiquetas con el ID de trabajo: " + jobId);
        // Espera a que el análisis de video se complete
        waitForJobCompletion(jobId, rekognitionClient);

        // Obtiene los resultados del análisis de video
        GetLabelDetectionResponse labelDetectionResponse = rekognitionClient.getLabelDetection(
                GetLabelDetectionRequest.builder().jobId(jobId).build());

        // Procesa los resultados del análisis de video
        List<LabelDetection> labelDetections = labelDetectionResponse.labels();
        HashMap<String, String> att = new HashMap<>();
        for (LabelDetection labelDetection : labelDetections) {
            if(labelDetection.label().confidence() > 95){
                att.put(labelDetection.label().name(), labelDetection.label().confidence().toString());
            }

        }
        for(String key: att.keySet()){
            System.out.println(key + " : " + att.get(key));
        }
        return att;
    }

    private static void waitForJobCompletion(String jobId, RekognitionClient rekognitionClient) {
        // Espera hasta que el análisis de video esté completo
        while (true) {
            GetLabelDetectionResponse labelDetectionResponse = rekognitionClient.getLabelDetection(
                    GetLabelDetectionRequest.builder().jobId(jobId).build());

            String status = labelDetectionResponse.jobStatus().toString();

            if (status.equals("SUCCEEDED") || status.equals("FAILED")) {
                break;
            }

            try {
                Thread.sleep(5000); // Espera 5 segundos antes de verificar el estado nuevamente
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }



}

