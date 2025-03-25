import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';


export interface Associate {
  id?: number;
  name: string;
  department: string;
}

@Injectable({
  providedIn: 'root'
})
export class AssociateService {

  private apiUrl = 'http://localhost:8085/api/v2/associates';

  constructor(private http:HttpClient) { }
  getAssociates(): Observable<Associate[]> {
    return this.http.get<Associate[]>(this.apiUrl);
  }

  getAssociateById(id: number): Observable<Associate> {
    return this.http.get<Associate>(`${this.apiUrl}/${id}`);
  }

  addAssociate(associate: Associate): Observable<Associate> {
  return this.http.post<Associate>(this.apiUrl, associate, {
    headers: { 'Content-Type': 'application/json' }  // ✅ Ensure JSON format
  });
}


updateAssociate(id: number, associate: Associate): Observable<Associate> {
  return this.http.put<Associate>(`${this.apiUrl}/${id}`, associate, {
    headers: { 'Content-Type': 'application/json' }
  });
}
deleteAssociate(id: number): Observable<void> {
  return this.http.delete<void>(`${this.apiUrl}/${id}`);
}
}
