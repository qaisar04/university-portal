package kz.baltabayev.gradingservice.model.dto;

/**
 * Data Transfer Object for the Grade entity.
 * This record encapsulates the data that can be transferred between processes or across the network.
 */
public record GradeResponse(Double score) {
}
