package me.anjwoc.management.domain

import jakarta.persistence.*
import me.anjwoc.management.domain.enums.IssuePriority
import me.anjwoc.management.domain.enums.IssueStatus
import me.anjwoc.management.domain.enums.IssueType

@Entity
@Table(name = "Issue")
class Issue (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column
    var userId: Long,

    @OneToMany(fetch = FetchType.EAGER)
    val comments: MutableList<Comment> = mutableListOf(),

    @Column
    var summary: String,

    @Column
    var description: String,

    @Column
    @Enumerated(EnumType.STRING)
    var type: IssueType,

    @Column
    @Enumerated(EnumType.STRING)
    var priority: IssuePriority,

    @Column
    @Enumerated(EnumType.STRING)
    var status: IssueStatus,

    ) : BaseEntity()