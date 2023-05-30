package genetic

internal class Population {
    private val populationList = mutableListOf<Specimen>()

    fun getPopulationFitness():Int{
        var tmpSumFitness = 0
        for (specimen in populationList){
            tmpSumFitness += specimen.getFitness()
        }
        return tmpSumFitness
    }
}