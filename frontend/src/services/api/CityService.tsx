class CityService {

    async getCityListByState(stateId: string){
        try {
            const response = await fetch(`http://localhost:8080/api/ms-people/city/query/by-state?id=${stateId}`, {
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

export default new CityService();