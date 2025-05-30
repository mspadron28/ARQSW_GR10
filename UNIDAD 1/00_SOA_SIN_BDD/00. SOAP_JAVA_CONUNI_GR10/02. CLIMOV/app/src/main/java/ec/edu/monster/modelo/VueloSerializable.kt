package ec.edu.monster.modelo

import android.os.Parcel
import android.os.Parcelable

data class VueloSerializable(
    val idVuelo: Int,
    val ciudadOrigen: String,
    val ciudadDestino: String,
    val valor: Double,
    val horaSalida: String,
    val fecha: Long
) : Parcelable {
    // Paso 2: Implementar describeContents
    override fun describeContents(): Int {
        return 0
    }

    // Paso 3: Implementar writeToParcel
    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeInt(idVuelo)
        dest.writeString(ciudadOrigen)
        dest.writeString(ciudadDestino)
        dest.writeDouble(valor)
        dest.writeString(horaSalida)
        dest.writeLong(fecha)
    }

    // Paso 4: Constructor para leer desde un Parcel
    constructor(parcel: Parcel) : this(
        idVuelo = parcel.readInt(),
        ciudadOrigen = parcel.readString() ?: "",
        ciudadDestino = parcel.readString() ?: "",
        valor = parcel.readDouble(),
        horaSalida = parcel.readString() ?: "",
        fecha = parcel.readLong()
    )

    // Paso 5: Companion object con CREATOR
    companion object CREATOR : Parcelable.Creator<VueloSerializable> {
        override fun createFromParcel(parcel: Parcel): VueloSerializable {
            return VueloSerializable(parcel)
        }

        override fun newArray(size: Int): Array<VueloSerializable?> {
            return arrayOfNulls(size)
        }
    }
}