fun main() {
    var semaforoAberto = true
    var carrosEsperando = 0

    Thread {
        while (true) {
            semaforoAberto = true
            println("Semáforo ABERTO")
            Thread.sleep(5000)

            semaforoAberto = false
            println("Semáforo FECHADO")
            Thread.sleep(10000)
        }
    }.start()

    Thread {
        var idCarro = 1

        while (true) {
            Thread.sleep((1000..3000).random().toLong())

            println("Carro $idCarro chegou")

            if (semaforoAberto) {
                println("Carro $idCarro passou")
            } else {
                println("Carro $idCarro está esperando")
                carrosEsperando++

                // espera abrir
                while (!semaforoAberto) {
                    Thread.sleep(500)
                }

                println("Carro $idCarro passou depois de esperar")
                carrosEsperando--
            }

            idCarro++
        }
    }.start()
}