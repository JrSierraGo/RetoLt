import { Sofkiano } from "../../types/Sofkiano"

class SofkianoService {

    getSofkianosList(page: number, size: number){
        return fetch(`http://localhost:8080/api/ms-people/sofkiano/query/all?page=${page}&size=${size}`,{
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json'
              },
        })
        .then((response:any) => response.json())
        .catch(error => console.error(error))
    }

    saveStatusChange(sofkiano: any) {
        console.log(sofkiano)
        return fetch(`http://localhost:8080/api/ms-people/sofkiano/command/manage-status`,{
        method: 'POST',
        body: JSON.stringify(sofkiano),
        headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json'
              },
        })
        .then((response:any) => response.json())
        .catch(error => console.error(error))
    }

}

export default new SofkianoService()
