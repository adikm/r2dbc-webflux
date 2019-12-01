package net.amarszalek.functionalspring

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.*


@Table
data class Country(@Column val name: String, @Column val continent: String)
