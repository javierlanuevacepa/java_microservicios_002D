package com._dmicroservicios.demo.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com._dmicroservicios.demo.Model.AuditoriaEvent;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;

@Service
public class SqsService {

    @Value("${aws.sqs.queue-url}")
    private String queueUrl;

    private final SqsClient sqsClient;
    private final ObjectMapper objectMapper;

    public SqsService(SqsClient sqsClient) {
        this.sqsClient = sqsClient;
        this.objectMapper = new ObjectMapper();
    }

    public void enviarAuditoria(AuditoriaEvent evento) {

        try {

            String json = objectMapper.writeValueAsString(evento);

            SendMessageRequest request = SendMessageRequest.builder()
                    .queueUrl(queueUrl)
                    .messageBody(json)
                    .build();

            sqsClient.sendMessage(request);

            System.out.println("Mensaje enviado a SQS.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}