
class SofkianoService {

    async getSofkianosList(page: number, size: number){
        try {
            const response = await fetch(`http://localhost:8080/api/ms-people/sofkiano/query/all?page=${page}&size=${size}`, {
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

    async saveStatusChange(sofkiano: any) {
        console.log(sofkiano)
        try {
            const response = await fetch(`http://localhost:8080/api/ms-people/sofkiano/command/manage-status`, {
                method: 'POST',
                body: JSON.stringify(sofkiano),
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

export default new SofkianoService()
