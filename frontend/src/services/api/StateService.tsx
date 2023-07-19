class StateService {

    async getStateListByCountry(countryId: string){
        try {
            const response = await fetch(`http://localhost:8080/api/ms-people/state/query/by-country?id=${countryId}`, {
                headers: {
                    'Content-Type': 'application/json',
                    'Accept': 'application/json'
                },
            })
            return response.json()
        } catch (error) {
            return console.error(error)
        }
    }

}

export default new StateService()