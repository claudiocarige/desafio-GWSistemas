package br.com.claudiocarige.mspersistencedb.core.dtos;

public record RequestDelivery(Long senderId, Long recipientId, Long productId, Integer quantity  ) {
}
