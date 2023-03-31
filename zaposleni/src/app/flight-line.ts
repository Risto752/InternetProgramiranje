export class FlightLine {



    constructor(public id : number, public starting_location: string,
        public destination: string, public flightType: string, public seatCount : number, public cargo_description: string, public townId : number,public arrivalFlag : boolean){}

}
