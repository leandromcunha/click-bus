/**
 * Leandro Marques da Cunha.
 * Projeto Alelo - Salesfoce
 * Sistema de Precificação
 *
 *
 */
package br.com.cunha.transacaolimite.convert;

import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE_TIME;

import java.io.IOException;
import java.time.LocalDateTime;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
/**
 * Classe JsonDateTimeDeserializer responsável por Deserializar para o Tipo
 * LocalDateTime.
 *
 * @author <a href="mailto:leandromcunha@gmail.com>Leandro Marques da Cunha</a>
 * @version $Id$ Leandro
 */
public class JsonDateTimeDeserializer extends JsonDeserializer<LocalDateTime> {

    private static final String NULL_VALUE = "null";

    /*
     * (non-Javadoc)
     *
     * @see
     * com.fasterxml.jackson.databind.JsonDeserializer#deserialize(com.fasterxml
     * .jackson.core.JsonParser,
     * com.fasterxml.jackson.databind.DeserializationContext)
     */
    @Override
    public LocalDateTime deserialize(final JsonParser jp,
                    final DeserializationContext ctxt)
        throws IOException, JsonProcessingException {
        ObjectCodec oc = jp.getCodec();
        JsonNode node = oc.readTree(jp);
        String dateString = node.textValue();

        LocalDateTime dateTime = null;
        if (!NULL_VALUE.equals(dateString)) {
            dateTime = LocalDateTime.parse(dateString, ISO_LOCAL_DATE_TIME);
        }
        return dateTime;
    }

}
