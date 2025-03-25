import { Component } from '@angular/core';
import { Associate, AssociateService } from '../../services/associate.service';

@Component({
  selector: 'app-associate',
  templateUrl: './associate.component.html',
  styleUrl: './associate.component.css'
})
export class AssociateComponent {

  associates: Associate[] = [];
  newAssociate: Associate = { name: '', department: '' };
  selectedAssociate: Associate | null = null;


  constructor(private associateService: AssociateService) {}


  ngOnInit(): void {
    this.loadAssociates();
  }

  loadAssociates() {
    this.associateService.getAssociates().subscribe(data => {
      this.associates = data;
    });
  }

  addAssociate() {
    if (!this.newAssociate.name || !this.newAssociate.department) {
      alert("Please enter name and department"); // ✅ Ensure form is filled
      return;
    }
    
    this.associateService.addAssociate(this.newAssociate).subscribe(response => {
      console.log("User added:", response);  // ✅ Debugging log
      this.newAssociate = { name: '', department: '' }; // Clear input fields
      this.loadAssociates(); // Refresh list
    }, error => {
      console.error("Error adding user:", error);
      alert("Failed to add user. Check console for details.");
    });
  }
  

  editAssociate(associate: Associate) {
    this.selectedAssociate = { ...associate }; // ✅ Copy object to avoid direct binding issues
  }

  updateAssociate() {
    if (this.selectedAssociate) {
      this.associateService.updateAssociate(this.selectedAssociate.id!, this.selectedAssociate).subscribe({
        next: () => {
          console.log("Associate updated successfully!");
          this.loadAssociates(); // ✅ Refresh list after update
          this.selectedAssociate = null; // ✅ Close edit form after update
        },
        error: error => console.error("Error updating associate:", error)
      });
    }
  }
  

  deleteAssociate(id: number) {
    this.associateService.deleteAssociate(id).subscribe({
      next: () => {
        console.log("Associate deleted successfully!");
        this.associates = this.associates.filter(a => a.id !== id); // ✅ Remove from list
      },
      error: error => console.error("Error deleting associate:", error)
    });
  }
  


}
