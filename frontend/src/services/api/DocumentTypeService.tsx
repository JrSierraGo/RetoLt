
class DocumentTypeService {

    async getAll(){
        try {
            const response = await fetch(`http://localhost:8080/api/ms-people/document-type/query/all`, {
                headers: {
                    'Content-Type': 'application/json',
                    'Accept': 'application/json'
                },
            })
            return response.json()
        } catch (error) {
            return {}
        }
    }

}

export default new DocumentTypeService()
