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


    public SqsService(SqsClient sqsClient) {
        this.sqsClient = sqsClient;
        
    }

    public void enviarAuditoria(AuditoriaEvent evento) {

        try {

           String json = String.format("""
           {
             "accion":"%s",
             "productoId":%d,
             "usuario":"%s",
             "fecha":"%s"
           }
           """,
                   evento.getAccion(),
                   evento.getProductoId(),
                   evento.getUsuario(),
                   evento.getFecha());

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