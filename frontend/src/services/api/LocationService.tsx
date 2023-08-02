
class LocationService {

    async getBySofkianoId(sofkianoId:string){
        try {
            const response = await fetch(`http://localhost:8080/api/ms-people/location/query/by-sofkiano?id=${sofkianoId}`, {
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

export default new LocationService()
