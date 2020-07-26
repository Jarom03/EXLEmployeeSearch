export class Employee { 
    public firstName: string;
    public lastName: string;
    public jobTitle: string;
    public birthDate: Date;
    public startDate: Date;
    public endDate: Date;

    constructor() {
        this.firstName = "";
        this.lastName = "";
        this.jobTitle = "";
        this.birthDate = undefined;
        this.startDate = undefined;
        this.endDate = undefined;
    }
}