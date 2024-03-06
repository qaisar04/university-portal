package kz.baltabayev.facultydepartmentservice.exception

class EntityNotFoundException(entityClass: Class<*>, id: Long) : RuntimeException(
    "Entity - ${entityClass.simpleName} with id '$id' not found."
)