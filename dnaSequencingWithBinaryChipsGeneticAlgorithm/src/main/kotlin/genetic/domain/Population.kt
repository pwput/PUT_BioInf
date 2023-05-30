package genetic.domain

class Population {
    val populationList = mutableListOf<Specimen>()

    fun getPopulationFitness():Int{
        var tmpSumFitness = 0
        for (specimen in populationList){
            tmpSumFitness += specimen.getFitness()
        }
        return tmpSumFitness
    }
}