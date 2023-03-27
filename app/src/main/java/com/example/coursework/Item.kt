package com.example.coursework

import androidx.room.*

@Entity (tableName = "parkings")
data class parking(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    @ColumnInfo(name = "type")
    var type: String,
    @ColumnInfo(name = "capacity")
    var capacity: String,
    @ColumnInfo(name = "address")
    var address: String,
)

@Entity (tableName = "Cars",
    foreignKeys = [
        ForeignKey(
            entity = parking::class,
            parentColumns = ["id"],
            childColumns = ["parkingId"],
            //onDelete = ForeignKey.CASCADE
            )
    ]
)
data class Cars(
    @PrimaryKey(autoGenerate = true)
    var carId: Int? = null,
    @ColumnInfo(name="carBrand")
    var carBrand : String,
    @ColumnInfo(name="model")
    var model : String,
    @ColumnInfo(name="regNumber")
    var regNumber : String,
    @ColumnInfo(name="createYear")
    var createYear : Int,
    @ColumnInfo(name="mileage")
    var mileage : String,
    @ColumnInfo(name="color")
    var color : String,
    @ColumnInfo(name="status")
    var status : String,
    @ColumnInfo(name="parkingId")
    var parkingId : Int,
    //@ColumnInfo(name="avatarUrl")
    //var avatarUrl : String
    )

/*@Entity (tableName = "Users",
    foreignKeys = [
        ForeignKey(
            entity = Cars::class,
            parentColumns = ["carId"],
            childColumns = ["bookedCarId"],

            )
    ]
)*/
@Entity (tableName = "Users")
data class Users(
    @PrimaryKey
    var login: String,
    @ColumnInfo(name="password")
    var password: String,
    @ColumnInfo(name="role")
    var role: String,
    @ColumnInfo(name="fullname")
    var fullname: String,
    @ColumnInfo(name="infoPass")
    var infoPass: String,
    @ColumnInfo(name="drivingExp")
    var drivingExp: String,
    @ColumnInfo(name="driverLicense")
    var driverLicense: String,
    @ColumnInfo(name="bookedCarId")
    var bookedCarId : Int
    )


@Entity (tableName = "Rta",
    foreignKeys = [
        ForeignKey(
            entity = Cars::class,
            parentColumns = ["carId"],
            childColumns = ["RtaCarId"],
            onDelete = ForeignKey.CASCADE
            )
    ]
)
data class Rta(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    @ColumnInfo(name="date")
    var date: String,
    @ColumnInfo(name="damage")
    var damage: String,
    @ColumnInfo(name = "contractNumber")
    var contractNumber: Int,
    @ColumnInfo(name="RtaCarId")
    var carId: Int
)

@Entity (tableName = "Contracts",
    foreignKeys = [
        ForeignKey(
            entity = Cars::class,
            parentColumns = ["carId"],
            childColumns = ["carId"],
            onDelete = ForeignKey.CASCADE
        ), ForeignKey(
            entity = Users::class,
            parentColumns = ["login"],
            childColumns = ["userLogin"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class Contracts(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    @ColumnInfo(name = "dateStart")
    var dateStart : String,
    @ColumnInfo(name="dateEnd")
    var dateEnd: String,
    @ColumnInfo(name="price")
    var price: String,
    @ColumnInfo(name="userLogin")
    var userLogin: String,
    @ColumnInfo(name="carId")
    var carId : Int
)
